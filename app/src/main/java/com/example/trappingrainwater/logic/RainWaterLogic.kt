package com.example.trappingrainwater.logic

import kotlin.random.Random

object RainWaterLogic {
    fun trap(height: Array<IntArray>): Array<IntArray> {
        val n = height.size
        if (n <= 2) return height

        var maxLeft = height[0][0]
        var maxRight = height[n-1][0]
        var leftPointer = 1
        var rightPointer = n-2

        while (leftPointer <= rightPointer) {
            if (maxLeft <= maxRight) {
                val newHieght = Math.max(0, maxLeft - height[leftPointer][0])
                height[leftPointer][1] = newHieght
                maxLeft = Math.max(maxLeft, height[leftPointer][0])
                leftPointer++
            } else {
                val newHeight = Math.max(0, maxRight - height[rightPointer][0])
                height[rightPointer][1] = newHeight
                maxRight = Math.max(maxRight, height[rightPointer][0])
                rightPointer--
            }
        }

        return height
    }

    fun initTrap(height: Int): Array<IntArray> {
        val result: Array<IntArray> = Array(height) {IntArray(2) {0} }

        for (res in result) {
            res[0] = Random.nextInt(1, 10)
        }
        return result
    }
}
