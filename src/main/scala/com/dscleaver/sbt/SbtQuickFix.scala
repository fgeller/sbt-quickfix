package com.dscleaver.sbt

import sbt._
import Keys._
import sbt.IO._
import quickfix.{ QuickFixLogger, QuickFixTestListener }

object SbtQuickFix extends Plugin {

  object QuickFixKeys {
    val quickFixDirectory = target in config("quickfix")
  }

  import QuickFixKeys._

  override val projectSettings = Seq(
    quickFixDirectory <<= target / "quickfix",
    extraLoggers <<= (quickFixDirectory, extraLoggers) apply { (target, currentFunction) ⇒ (key: ScopedKey[_]) ⇒ {
      val loggers = currentFunction(key)
      val taskOption = key.scope.task.toOption
      taskOption.map(_.label) match {
        case Some(task) if task.toLowerCase.contains("compile") ⇒
          new QuickFixLogger(target / "sbt.quickfix") +: loggers
        case _ ⇒
          loggers
      }
    }
    },
    testListeners <+= (quickFixDirectory, sources in Test) map { (target, testSources) ⇒
      QuickFixTestListener(target / "sbt.quickfix", testSources)
    }
  )
}
