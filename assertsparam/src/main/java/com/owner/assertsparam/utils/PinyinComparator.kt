package com.owner.assertsparam.utils

import com.owner.assertsparam.data.Manager
import java.util.Comparator

/**
 * 拼音比较
 * @author xiaanming
 */
class PinyinComparator : Comparator<Manager> {

    override fun compare(o1: Manager, o2: Manager): Int {
        return if (o1.letters == "@" || o2.letters == "#") {
            1
        } else if (o1.letters == "#" || o2.letters == "@") {
            -1
        } else {
            o1.letters.compareTo(o2.letters)
        }
    }

}
