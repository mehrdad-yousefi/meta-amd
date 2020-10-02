PR := "${INC_PR}.1"

KMACHINE_amdx86 ?= "common-pc-64"
SRCREV_meta_amdx86 ??= "94bb91f62b8ea78d1b87eef6fc348035c405726a"
LINUX_VERSION_amdx86 ??= "5.4.2"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-yocto-${LINUX_VERSION}:"

SRC_URI_append_amdx86 = " file://amd-xgbe-patches.scc \
                   file://amd-emmc-patches.scc \
                   file://logo.cfg \
                   file://console.cfg \
                   file://sound.cfg \
                   file://hid.cfg \
                   file://usb-serial.cfg \
                   file://wifi-drivers.cfg \
                   file://disable-intel-graphics.cfg \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'file://enable-bluetooth.cfg', 'file://disable-bluetooth.cfg', d)} \
                   ${@bb.utils.contains('DISTRO', 'poky-amd', 'file://enable-kgdb.cfg', 'file://disable-kgdb.cfg', d)}"

KERNEL_FEATURES_append_amdx86 = " cfg/smp.scc"
