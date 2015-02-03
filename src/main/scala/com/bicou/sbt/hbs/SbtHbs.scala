package com.bicou.sbt.hbs

import sbt._
import sbt.Keys._
import com.typesafe.sbt.web._
import com.typesafe.sbt.jse.SbtJsTask
import spray.json._

object Import {

  object HbsKeys {
    val hbs = TaskKey[Seq[File]]("hbs", "Precompile handlebar templates.")

    val amd = SettingKey[Boolean]("hbs-amd", "Exports amd style (require.js)")
    val commonjs = SettingKey[String]("hbs-commonjs", "Exports CommonJS style, path to Handlebars module")
    val handlebarPath = SettingKey[String]("hbs-handlebarPath", "Path to handlebar.js (only valid for amd-style)")
    val min = SettingKey[Boolean]("hbs-min", "Minimize output")
    val known = SettingKey[Seq[String]]("hbs-known", "Known helpers")
    val knownOnly = SettingKey[Boolean]("hbs-knownOnly", "Known helpers only")
    val namespace = SettingKey[String]("hbs-namespace", "Template namespace")
    val root = SettingKey[String]("hbs-root", "Template root (base value that will be stripped from template names)")
    val data = SettingKey[Boolean]("hbs-data", "Include data when compiling")
    val bom = SettingKey[Boolean]("hbs-bom", "Removes the BOM (Byte Order Mark) from the beginning of the templates")
    val simple = SettingKey[Boolean]("hbs-simple", "Output template function only")
  }

}

object SbtHbs extends AutoPlugin {

  override def requires = SbtJsTask

  override def trigger = AllRequirements

  val autoImport = Import

  import SbtWeb.autoImport._
  import WebKeys._
  import SbtJsTask.autoImport.JsTaskKeys._
  import autoImport.HbsKeys._
  import com.typesafe.sbt.jse.JsEngineImport.JsEngineKeys._

  val hbsUnscopedSettings = Seq(

    excludeFilter := HiddenFileFilter,
    includeFilter := "*.hbs" || "*.handlebars",

    jsOptions := JsObject(
      "amd" -> JsBoolean(amd.value),
      "commonjs" -> JsString(commonjs.value),
      "handlebarPath" -> JsString(handlebarPath.value),
      "min" -> JsBoolean(min.value),
      "known" -> JsArray(known.value.toList.map(JsString(_))),
      "knownOnly" -> JsBoolean(knownOnly.value),
      "namespace" -> JsString(namespace.value),
      "root" -> JsString(root.value),
      "data" -> JsBoolean(data.value),
      "bom" -> JsBoolean(bom.value),
      "simple" -> JsBoolean(simple.value)
    ).toString()
  )

  override def projectSettings = Seq(
    amd := false,
    commonjs := "",
    handlebarPath := "",
    min := false,
    known := Seq(),
    knownOnly := false,
    namespace := "Handlebars.templates",
    root := "",
    data := false,
    bom := false,
    simple := false

  ) ++ inTask(hbs)(
    SbtJsTask.jsTaskSpecificUnscopedSettings ++
      inConfig(Assets)(hbsUnscopedSettings) ++
      inConfig(TestAssets)(hbsUnscopedSettings) ++
      Seq(
        moduleName := "hbs",
        shellFile := getClass.getClassLoader.getResource("handlebars-shell.js"),

        taskMessage in Assets := "Handlebars compiling",
        taskMessage in TestAssets := "Handlebars test compiling"
      )
  ) ++ SbtJsTask.addJsSourceFileTasks(hbs) ++ Seq(
    hbs in Assets := (hbs in Assets).dependsOn(nodeModules in Assets).value,
    hbs in TestAssets := (hbs in TestAssets).dependsOn(nodeModules in TestAssets).value,

    nodeModuleGenerators in Plugin <+= npmNodeModules in Assets,
    nodeModuleDirectories in Plugin += baseDirectory.value / "node_modules"
  )

}

// vim: set ts=2 sw=2 et:
