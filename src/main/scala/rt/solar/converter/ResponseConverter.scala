package rt.solar.converter

import rt.solar.dto.{Response, ResponseItem, SOFResponse}

object ResponseConverter {

  // TODO parallelize for large sequences of data
  def convert(data: Seq[SOFResponse]): Response = {
    val items = data.view
      .flatMap(_.items)
      .flatMap(i => i.tags.map(t => ResponseItem(t, 1, i.is_answered.toInt)))
      .groupMapReduce(_.name)(identity) { (a, b) =>
        a.copy(total = a.total + b.total, answered = a.answered + b.answered)
      }
      .values
    Response(items.toSeq)
  }

  private implicit class BoolToIntOps(val boolean: Boolean) extends AnyVal {
    def toInt: Int = if (boolean) 1 else 0
  }

}
