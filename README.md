Scala Presentation
==================
Application built with the following (main) technologies:

- Scala

- SBT

- Specs2

Introduction
------------
Examples of using Scala as a high level introduction, but also some funky stuff as well.

Build and Deploy
----------------
The project is built with SBT. On a Mac (sorry everyone else) do:
> brew install sbt

It is also a good idea to install Typesafe Activator (which sits on top of SBT) for when you need to create new projects - it also has some SBT extras, so running an application with Activator instead of SBT can be useful. On Mac do:
> brew install typesafe-activator

To compile:
> sbt compile

or
> activator compile

To run the specs:
> sbt test

Ammonite-REPL in SBT
--------------------
> sbt test:console