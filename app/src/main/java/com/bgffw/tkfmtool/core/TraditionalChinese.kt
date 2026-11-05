package com.bgffw.tkfmtool.core

/**
 * 把遊戲繁體介面的詞條文字轉成角色庫使用的簡體。
 *
 * 只收錄詞條會用到的字，不是通用繁簡轉換。
 * 「間」「闇」刻意不轉：前者要交給硬編碼規則修正，後者本來就是角色庫的寫法。
 */
object TraditionalChinese {

    private val MAP: Map<Char, Char> = mapOf(
        '領' to '领',
        '屬' to '属',
        '風' to '风',
        '亞' to '亚',
        '類' to '类',
        '體' to '体',
        '擊' to '击',
        '護' to '护',
        '療' to '疗',
        '礙' to '碍',
        '輔' to '辅',
        '發' to '发',
        '戰' to '战',
        '強' to '强',
        '輸' to '输',
        '貧' to '贫',
        '禦' to '御',
        '復' to '复',
        '擾' to '扰'
    )

    fun toSimplified(text: String): String {
        val out = StringBuilder(text.length)
        for (c in text) out.append(MAP[c] ?: c)
        return out.toString()
    }
}
