@file:Suppress("PackageDirectoryMismatch", "InvalidPackageDeclaration")
package androidx.lifecycle

fun ViewModelStore.getAll() = keys().map { get(it) }.toSet()
