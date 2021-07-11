package main.scala.joins

import main.scala.comparisons.Equalable
import main.scala.joins.LeftJoin.leftLinear

object InnerJoin {

  // TODO, think about creating a InnerJoinResult class. This could:
  // - Ensure that the right sequence is not empty.
  // - provide the toCaresian function.
  type NonEmptySeq[A] = Seq[A]

  def innerLinear[A,B](left: Seq[A], right: Seq[B])(implicit eq: Equalable[A,B]): Seq[(A, NonEmptySeq[B])] =  {
    leftLinear(left, right).filter(rec => !rec._2.isEmpty)
  }

  def innerLinear[A,B,C](left: Seq[A], right: Seq[B], reduceLeftToPrimaryKey: A => C, reduceRightToPrimaryKey: B => C)
                       (implicit eq: Equalable[C,C]): Seq[(A, NonEmptySeq[B])] = {
    leftLinear(left, right, reduceLeftToPrimaryKey, reduceRightToPrimaryKey).filter(rec => !rec._2.isEmpty)
  }


}