FILESEXTRAPATHS:prepend := "${THISDIR}/linux-amd:"

SRC_URI:append:v3000 = " file://xgbe.cfg file://amd_io.cfg"

require linux-amd.inc

do_install() {
    install -d ${D}/lib/modules/${KERNEL_VERSION}/
    oe_runmake INSTALL_MOD_PATH=${D}/ modules_install
}

FILES:${KERNEL_PACKAGE_NAME}-modules += "/boot"
FILES:${KERNEL_PACKAGE_NAME}-modules += "/lib/modules/${KERNEL_VERSION}/modules*"
PACKAGES =+ "extra-modules"

LINUX_VERSION_EXTENSION = "-amd-${LINUX_KERNEL_TYPE}"

KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc \
                            features/security/security.scc"

KERNEL_FEATURES:append = " ${KERNEL_EXTRA_FEATURES}"
