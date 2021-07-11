package com.eggerling.scalajoins.testing

import org.scalatest.funsuite.AnyFunSuite
import com.eggerling.scalajoins.comparisons.{Equalable, LessThanable}
import com.eggerling.scalajoins.joins.LeftJoin.{leftBinary, leftLinear, toCartesian}


class LeftJoinTest extends AnyFunSuite {
  test("leftLinearDirectComp Int-Int") {
    val l = List(1,2,3,4)
    val r = List(6,4,1,4)
    val expected = List((1, List(1)), (2, List()), (3, List()), (4, List(4,4)))
    assert(leftLinear(l,r) == expected)
  }

  test("leftLinearDirectComp Int-String") {
    val l = List(1,2,3,4)
    val r = List("6","4","1","4")
    val expected = List((1, List("1")), (2, List()), (3, List()), (4, List("4","4")))
    assert(leftLinear(l,r)((a: Int, b: String) => a == b.toInt) == expected)
  }

  test("leftBinaryDirectComp Int-Int") {
    val l = List(1,2,3,4)
    val r = Vector(6,4,1,4)
    val expected = List((1, List(1)), (2, List()), (3, List()), (4, List(4,4)))
    assert(leftBinary(l,r) == expected)
  }

  test("leftBinaryrDirectComp Int-String") {
    val l = List(1,2,3,4)
    val r = Vector("6","4","1","4")
    val expected = List((1, List("1")), (2, List()), (3, List()), (4, List("4","4")))
    val ltAB: LessThanable[Int, String] = (a: Int, b: String) => a < b.toInt
    val ltBB: LessThanable[String, String] = (a: String, b: String) => a < b
    val eqAB: Equalable[Int, String] = (a: Int, b: String) => a == b.toInt
    assert(leftBinary(l,r)(ltAB, ltBB, eqAB) == expected)
  }

  test("leftLinearReduced Int-String, Int") {
    val l = List(1,2,3,4)
    val r = List("6","4","1","4")
    val expected = List((1, List("1")), (2, List()), (3, List()), (4, List("4","4")))
    assert(leftLinear(l,r, identity[Int], (b: String) => b.toInt) == expected)
  }

  test("leftBinaryReduced Int-String, Int") {
    val l = List(1,2,3,4)
    val r = Vector("6","4","1","4")
    val expected = List((1, List("1")), (2, List()), (3, List()), (4, List("4","4")))
    assert(leftBinary(l, r, identity[Int], (b: String) => b.toInt) == expected)
  }

  test("toCartesian") {
    val l = List((1, List(1)), (2, List()), (3, List()), (4, List(4,4)))
    val cart = toCartesian(l)
    val expected = List((1, Some(1)), (2, None), (3, None), (4, Some(4)), (4, Some(4)))
    assert(cart == expected)
  }

}
