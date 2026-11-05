package com.bgffw.tkfmtool.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import java.io.File

class DisplayTextTest {

    private val rows: List<List<String>> = File("src/main/assets/characters.tsv")
        .readLines(Charsets.UTF_8)
        .filter { it.isNotBlank() }
        .map { it.split('\t') }

    @Test
    fun everyTagConvertsBackToTheSameDatabaseTag() {
        for (row in rows) {
            for (cell in row.drop(1)) {
                if (cell == "无词条") continue
                assertEquals(cell, TraditionalChinese.toSimplified(DisplayText.tag(cell)))
            }
        }
    }

    @Test
    fun rowKeepsStarPrefixUsedForColoring() {
        assertEquals("3☆魔王 巴爾,領袖,火屬性", DisplayText.row(listOf("3☆魔王 巴尔", "领袖", "火属性")))
    }

    @Test
    fun noSimplifiedCharactersRemainInDisplayedRows() {
        val simplified = "领属风亚类体击护疗碍辅发战强输贫复扰尔灵赛亚兰龙圣骑剑将军优爱猫传"
        for (row in rows) {
            val shown = DisplayText.row(row)
            assertFalse(shown, shown.any { it in simplified })
        }
    }

    @Test
    fun displayingDoesNotChangeMatching() {
        val data = listOf(listOf("3☆魔王 巴尔", "领袖", "火属性"))
        val before = TagMatcher.build(listOf("领袖", "火属性"), data)
        data.forEach { DisplayText.row(it) }
        val after = TagMatcher.build(listOf("领袖", "火属性"), data)
        assertEquals(before.map { it.tags }, after.map { it.tags })
    }
}
