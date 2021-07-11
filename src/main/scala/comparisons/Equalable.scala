package main.scala.comparisons

trait Equalable[A,B] {
  def eq(a:A, b:B): Boolean
}

object Equalable {
  // Create an implicit Equalable when A and B are the same and already posses an Ordering.
  implicit def e[A](implicit ord: Ordering[A]): Equalable[A,A] = (a: A, b: A) => ord.equiv(a,b)

}
