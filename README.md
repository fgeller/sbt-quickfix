# sbt-quickfix

This is an sbt plugin that generates a quickfix file.

Inspired by [Alois Cochard's blog post] on setting up quickfix using a bash wrapper around sbt.

## Usage

Build and install the project

    git clone git@github.com:kalmanb/sbt-quickfix.git
    cd sbt-quickfix
    sbt
    > publishLocal

Add this to your `~/.sbt/0.13/plugins/plugins.sbt`:

    addSbtPlugin("com.dscleaver.sbt" % "sbt-quickfix" % "0.4.3")
