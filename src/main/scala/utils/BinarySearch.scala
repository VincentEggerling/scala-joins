package main.scala.utils

import main.scala.comparisons.{Equalable, LessThanable}

import scala.annotation.tailrec

object BinarySearch {


  def binaryFilter[A,B](target: A, sortedSeq: IndexedSeq[B])(implicit eqAB: Equalable[A,B], ltAB: LessThanable[A,B]): Seq[B] = {
    val index = binaryFindIndex(target, sortedSeq,0)
    if (index != -1)
      takeAroundWhile(target, sortedSeq, index)
    else
      Seq.empty[B] // TODO: Maybe don't use Seq although that should not really matter if it's empty ?
  }

  @tailrec
  def binaryFindIndex[A,B](target: A, sortedSeq: IndexedSeq[B], indexOffset: Int)(implicit eqAB: Equalable[A,B], ltAB: LessThanable[A,B]): Int = {
    if (sortedSeq.isEmpty)
      -1
    else {
      val mid = sortedSeq.size/2
      val midElem = sortedSeq(mid)
      if (eqAB.eq(target, midElem)) {
        mid + indexOffset
      } else {
        if (ltAB.lt(target, midElem)) {
          binaryFindIndex(target, sortedSeq.slice(0, mid), indexOffset)
        } else {
          binaryFindIndex(target, sortedSeq.slice(mid+1, sortedSeq.size), indexOffset + mid + 1)
        }
      }
    }
  }



  def takeAroundWhile[A,B](target: A, seq: IndexedSeq[B], fromIndex: Int)(implicit eqAB: Equalable[A,B]): Seq[B] = {
    val leftSeq = takeLeftWhile(target, seq, fromIndex)
    val rightSeq = takeRightWhile(target, seq, fromIndex)
    leftSeq ++ rightSeq.drop(1)
  }

  def takeLeftWhile[A,B](target: A, seq: IndexedSeq[B], fromIndex: Int)(implicit eqAB: Equalable[A,B]): Seq[B] = {
    var i = fromIndex
    while (i >= 0 && eqAB.eq(target, seq(i))) i = i-1
    seq.slice(i+1, fromIndex+1)
  }

  def takeRightWhile[A,B](target: A, seq: IndexedSeq[B], fromIndex: Int)(implicit eqAB: Equalable[A,B]): Seq[B] = {
    var i = fromIndex
    while (i < seq.size && eqAB.eq(target, seq(i))) i = i+1
    seq.slice(fromIndex, i)
  }

}
