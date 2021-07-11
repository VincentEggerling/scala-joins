package main.scala.comparisons

trait LessThanable[A,B] {
  def lt(a: A,b: B): Boolean
}

object LessThanable {
  // Create an implicit LessThanable when A and B are the same and already posses an Ordering.
  implicit def e[A](implicit ord: Ordering[A]): LessThanable[A,A] = (a: A, b: A) => ord.lt(a,b)

}
