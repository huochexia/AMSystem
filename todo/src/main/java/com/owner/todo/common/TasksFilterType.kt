package com.owner.todo.common

/**
 *
 * Created by Liuyong on 2018-12-09.It's AMSystem
 *@description:
 */
enum class TasksFilterType {
    /**
     * 全部，不进行过滤
     */
    ALL_TASKS,
    /**
     * 过滤活动状态 即未完成任务
     */
    ACTIVE_TASKS,
    /**
     * 过滤已完成任力
     */
    COMPLETED_TASKS
}