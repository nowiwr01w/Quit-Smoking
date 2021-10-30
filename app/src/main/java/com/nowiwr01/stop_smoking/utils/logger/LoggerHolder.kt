package com.nowiwr01.stop_smoking.utils.logger

import com.nowiwr01.stop_smoking.utils.logger.model.Action
import com.nowiwr01.stop_smoking.utils.logger.model.Category

class LoggerHolder(
    private val loggers: MutableCollection<ILogger> = HashSet()
) : ILogger, MutableCollection<ILogger> {

    override val size: Int
        get() = loggers.size

    override fun contains(element: ILogger): Boolean = loggers.contains(element)

    override fun containsAll(elements: Collection<ILogger>): Boolean = loggers.containsAll(elements)

    override fun isEmpty(): Boolean = loggers.isEmpty()

    override fun iterator(): MutableIterator<ILogger> = loggers.iterator()

    override fun add(element: ILogger): Boolean = loggers.add(element)

    override fun addAll(elements: Collection<ILogger>): Boolean = loggers.addAll(elements)

    override fun clear() = loggers.clear()

    override fun remove(element: ILogger): Boolean = loggers.remove(element)

    override fun removeAll(elements: Collection<ILogger>): Boolean = loggers.removeAll(elements)

    override fun retainAll(elements: Collection<ILogger>): Boolean = loggers.retainAll(elements)

    override fun logEvent(category: Category, action: Action, value: String) {
        for (logger in loggers) {
            logger.logEvent(category, action, value)
        }
    }
}