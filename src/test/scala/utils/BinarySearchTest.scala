package test.scala.utils

import main.scala.utils.BinarySearch.{binaryFilter, binaryFindIndex, takeAroundWhile, takeLeftWhile, takeRightWhile}
import org.scalatest.funsuite.AnyFunSuite

class BinarySearchTest extends AnyFunSuite {
  test("binaryFindIndex") {
    val l = Vector(1,2,3,4,5,6,7,8,9,10)
    assert(binaryFindIndex(0, l, 0) == -1)
    assert(binaryFindIndex(1, l, 0) == 0)
    assert(binaryFindIndex(2, l, 0) == 1)
    assert(binaryFindIndex(3, l, 0) == 2)
    assert(binaryFindIndex(4, l, 0) == 3)
    assert(binaryFindIndex(5, l, 0) == 4)
    assert(binaryFindIndex(6, l, 0) == 5)
    assert(binaryFindIndex(7, l, 0) == 6)
    assert(binaryFindIndex(8, l, 0) == 7)
    assert(binaryFindIndex(9, l, 0) == 8)
    assert(binaryFindIndex(10, l, 0) == 9)
    assert(binaryFindIndex(11, l, 0) == -1)

  }

  test("takeLeftWhile") {
    val l = Vector(1,1,2,3,3,3,4,4,4,4,5,6,6)
    assert(takeLeftWhile(3, l, 0) == Vector())
    assert(takeLeftWhile(3, l, 1) == Vector())
    assert(takeLeftWhile(3, l, 2) == Vector())
    assert(takeLeftWhile(3, l, 3) == Vector(3))
    assert(takeLeftWhile(3, l, 4) == Vector(3,3))
    assert(takeLeftWhile(3, l, 5) == Vector(3,3,3))
    assert(takeLeftWhile(3, l, 6) == Vector())
    assert(takeLeftWhile(3, l, 7) == Vector())
    assert(takeLeftWhile(3, l, 12) == Vector())

    assert(takeLeftWhile(1, l, 0) == Vector(1))
    assert(takeLeftWhile(1, l, 1) == Vector(1,1))
    assert(takeLeftWhile(1, l, 2) == Vector())

    assert(takeLeftWhile(6, l, 10) == Vector())
    assert(takeLeftWhile(6, l, 11) == Vector(6))
    assert(takeLeftWhile(6, l, 12) == Vector(6,6))
  }

  test("takeRightWhile") {
    val l = Vector(1,1,2,3,3,3,4,4,4,4,5,6,6)
    assert(takeRightWhile(3, l, 0) == Vector())
    assert(takeRightWhile(3, l, 1) == Vector())
    assert(takeRightWhile(3, l, 2) == Vector())
    assert(takeRightWhile(3, l, 3) == Vector(3,3,3))
    assert(takeRightWhile(3, l, 4) == Vector(3,3))
    assert(takeRightWhile(3, l, 5) == Vector(3))
    assert(takeRightWhile(3, l, 6) == Vector())
    assert(takeRightWhile(3, l, 7) == Vector())
    assert(takeRightWhile(3, l, 12) == Vector())

    assert(takeRightWhile(1, l, 0) == Vector(1,1))
    assert(takeRightWhile(1, l, 1) == Vector(1))
    assert(takeRightWhile(1, l, 2) == Vector())

    assert(takeRightWhile(6, l, 10) == Vector())
    assert(takeRightWhile(6, l, 11) == Vector(6,6))
    assert(takeRightWhile(6, l, 12) == Vector(6))
  }

  test("takeAroundWhile") {
    val l = Vector(1,1,2,3,3,3,4,4,4,4,5,6,6)
    assert(takeAroundWhile(3, l, 0) == Vector())
    assert(takeAroundWhile(3, l, 1) == Vector())
    assert(takeAroundWhile(3, l, 2) == Vector())
    assert(takeAroundWhile(3, l, 3) == Vector(3,3,3))
    assert(takeAroundWhile(3, l, 4) == Vector(3,3,3))
    assert(takeAroundWhile(3, l, 5) == Vector(3,3,3))
    assert(takeAroundWhile(3, l, 6) == Vector())
    assert(takeAroundWhile(3, l, 7) == Vector())
    assert(takeAroundWhile(3, l, 12) == Vector())

    assert(takeAroundWhile(1, l, 0) == Vector(1,1))
    assert(takeAroundWhile(1, l, 1) == Vector(1,1))
    assert(takeAroundWhile(1, l, 2) == Vector())

    assert(takeAroundWhile(6, l, 10) == Vector())
    assert(takeAroundWhile(6, l, 11) == Vector(6,6))
    assert(takeAroundWhile(6, l, 12) == Vector(6,6))
  }

  test("binaryFilter") {
    val l = Vector(1,1,2,3,3,3,4,4,4,4,5,6,6)
    assert(binaryFilter(0, l) == Vector())
    assert(binaryFilter(1, l) == Vector(1,1))
    assert(binaryFilter(2, l) == Vector(2))
    assert(binaryFilter(3, l) == Vector(3,3,3))
    assert(binaryFilter(4, l) == Vector(4,4,4,4))
    assert(binaryFilter(5, l) == Vector(5))
    assert(binaryFilter(6, l) == Vector(6,6))
    assert(binaryFilter(7, l) == Vector())
  }



}
