# Scala Mutagen

Scala Mutagen is a collection of various things I've wanted to have in Scala from other languages, including Haskell, Clojure, and Kotlin.

Some of these are implemented within the Scala programming language, others make use of metaprogramming, some are implemented as compiler plugins.

## Repo Structure

1. [core] Things that can be done without compiler plugins.
1. [plugins](plugins) The Scala compiler plugins.
1. [use-plugins](use-plugins) Examples using the plugins created above. Each project in here is named after the plugin it uses.

## Enhancements

### Let/Where

#### A simple Scala project that uses the [division-by-zero plugin](plugins/division-by-zero).

First, compile and publish the plugin as a jar file to your local Ivy repo:

```scala
cd plugins/division-by-zero
sbt compile package publishLocal
```

Next, use the plugin in your project by adding to build.sbt following lines for Scala 2:

```scala
autoCompilerPlugins := true
addCompilerPlugin("io.mattmoore.scala.mutagen.plugins" %% "division-by-zero" % "0.0.1-SNAPSHOT")
resolvers += Resolver.mavenLocal
```

For Scala 3, change this to (note the namespace has changed `scala2` to `scala3`):

```scala
autoCompilerPlugins := true
addCompilerPlugin("io.mattmoore.scala.mutagen.plugins" %% "division-by-zero" % "0.0.1-SNAPSHOT")
resolvers += Resolver.mavenLocal
```

The `build.sbt` configuration above is in the [use-plugins/division-by-zero/build.sbt](use-plugins/division-by-zero/build.sbt) file already.

You can experiment with turning the plugin on or off. Just comment the `addCompilerPlugin` line out to disable it.

When the plugin is disabled, the [division-by-zero example](use-plugins/division-by-zero) project will compile successfully. When you run it, the program will fail because it attempts to do division by zero.

When the plugin is enabled, the project will fail to compile, throwing an error that division by zero is being attempted.

## Why are there two different namespaces for Scala 2 and Scala 3 for the same plugin?

TLDR: Because plugins for Scala 2 and Scala 3 are incompatible, and are therefore _not the same_. Scala 3 has changed the plugin system and it is quite different from the way Scala 2 works.

Scala 3 removed the older analysis plugins that Scala 2 had. Instead, it now provides the "StandardPlugin" and "ResearchPlugin" types. Standard plugins do not have the ability to modify type information at compile time anymore. This is to prevent a compiler author from breaking type safety. If you need to fully control all phases, you would instead create a research plugin that allows full control of the compilation pipeline. For more information, see [Changes in Compiler Plugins](https://dotty.epfl.ch/docs/reference/changed-features/compiler-plugins.html).
