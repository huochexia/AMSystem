package com.owner.assertsparam.utils

import java.util.Comparator

/**
 *
 * @author xiaanming
 */
class PinyinComparator : Comparator<GroupMemberBean> {

    override fun compare(o1: GroupMemberBean, o2: GroupMemberBean): Int {
        return if (o1.sortLetters == "@" || o2.sortLetters == "#") {
            1
        } else if (o1.sortLetters == "#" || o2.sortLetters == "@") {
            -1
        } else {
            o1.sortLetters!!.compareTo(o2.sortLetters!!)
        }
    }

}
