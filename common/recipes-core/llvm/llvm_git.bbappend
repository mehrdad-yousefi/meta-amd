FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=c520ed40e11887bb1d24d86f7f5b1f05"

SRCREV = "4a059213dd6f034147e9083c21133dc1b57b3a8a"
PV = "7"
BRANCH = "release_70"
PATCH_VERSION = "0"

SRC_URI += "file://0001-llvm-config-allow-overriding-libdir-through-cmdline.patch"

SRC_URI_remove="file://0001-Disable-generating-a-native-llvm-config.patch"

INSANE_SKIP_${MLPREFIX}libllvm${LLVM_RELEASE}-llvm-${LLVM_RELEASE}.${PATCH_VERSION}.0 += "dev-so"
INSANE_SKIP_${PN} += "installed-vs-shipped"
