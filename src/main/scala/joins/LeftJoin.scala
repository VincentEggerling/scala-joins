package com.eggerling.scalajoins.joins

import com.eggerling.scalajoins.comparisons.{Equalable, LessThanable}
import com.eggerling.scalajoins.utils.BinarySearch.binaryFilter

object LeftJoin {

  // Runs in O(A*B)
  def leftLinear[A,B](left: Seq[A], right: Seq[B])(implicit eq: Equalable[A,B]): Seq[(A, Seq[B])] =
    left.map(l => (l, right.filter(r => eq.eq(l,r))))

  // Runs in O(A*mapA + B*mapB + A*B). Assuming the reducing functions are constant time, this runs in O(A*B)
  def leftLinear[A,B,C](left: Seq[A], right: Seq[B], reduceLeftToPrimaryKey: A => C, reduceRightToPrimaryKey: B => C)
                    (implicit eq: Equalable[C,C]): Seq[(A, Seq[B])] = {
    val leffWithC: Seq[(A,C)] = left.map(l => (l, reduceLeftToPrimaryKey(l))) // O(A*mapA)
    val rightWithC: Seq[(B,C)] = right.map(r => (r, reduceRightToPrimaryKey(r))) // O(B*mapB)
    leffWithC.map(l => (l._1, rightWithC.filter(r => eq.eq(l._2, r._2)).map(_._1))) // O(A*B)
  }

  // Runs in O((A+B)log(B))
  def leftBinary[A,B](left: Seq[A], right: IndexedSeq[B])
                  (implicit ltAB: LessThanable[A,B], ltBB: LessThanable[B,B], eqAB: Equalable[A,B]): Seq[(A, Seq[B])] = {
    val sortedRight = right.sortWith(ltBB.lt) // O(B*log(B))
    left.map(l => (l, binaryFilter(l, sortedRight))) // O(A*log(B))
  }

  def leftBinary[A,B,C](left: Seq[A], right: IndexedSeq[B], reduceLeftToPrimaryKey: A => C, reduceRightToPrimaryKey: B => C)
                    (implicit eqC: Equalable[C,C], ltC: LessThanable[C,C]): Seq[(A, Seq[B])] = {
    val leffWithC: Seq[(A,C)] = left.map(l => (l, reduceLeftToPrimaryKey(l))) // O(A*mapA)
    val rightWithC: IndexedSeq[(B,C)] = right.map(r => (r, reduceRightToPrimaryKey(r))) // O(B*mapB)
    val sortedRightWithC: IndexedSeq[(B,C)] = rightWithC.sortWith((tuple1: (B,C), tuple2: (B,C)) => ltC.lt(tuple1._2, tuple2._2)) // O(B*log(B))

    val eqACBC: Equalable[(A,C),(B,C)] = (x:(A,C), y:(B,C)) => eqC.eq(x._2, y._2)
    val ltACBC: LessThanable[(A,C),(B,C)] = (x:(A,C), y:(B,C)) => ltC.lt(x._2, y._2)
    val res: Seq[((A,C), Seq[(B,C)])] = leffWithC.map(lAC => (lAC, binaryFilter(lAC, sortedRightWithC)(eqACBC, ltACBC))) // O(A*log(B))
    res.map(x => (x._1._1, x._2.map(_._1))) // O(max(A,B)) hopefully ? Depends on the number of matched key.
  }


  def toCartesian[A,B](s: Seq[(A,Seq[B])]): Seq[(A, Option[B])] = s.flatMap(flattenRecord)

  private def flattenRecord[A,B](rec: (A, Seq[B])): Seq[(A, Option[B])] = {
    if (rec._2.isEmpty) {
      Seq((rec._1, None)) // TODO Maybe don't use Seq here even though the implementation shouldn't matter too much if it's empty.
    } else {
      rec._2.map(x => (rec._1, Some(x)))
    }
  }
}
