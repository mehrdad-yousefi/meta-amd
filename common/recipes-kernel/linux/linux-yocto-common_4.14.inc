FILESEXTRAPATHS_prepend := "${THISDIR}/linux-yocto-${LINUX_VERSION}:"

PR := "${INC_PR}.1"

KMACHINE_amdx86 ?= "common-pc-64"
SRCREV_meta_amdx86 ?= "1fb0b0379fb5883ce5af7485374e3f78ee4272d3"
LINUX_VERSION_amdx86 ?= "4.14.71"

SRC_URI_append_amdx86 += " \
	file://upstream-backports.scc \
	file://hid.cfg \
	file://efi-partition.cfg \
	file://usb-serial.cfg \
	file://wifi-drivers.cfg \
	${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'file://enable-bluetooth.cfg', 'file://disable-bluetooth.cfg', d)} \
	${@bb.utils.contains('DISTRO', 'mel', 'file://enable-kgdb.cfg', 'file://disable-kgdb.cfg', d)} \
"

KERNEL_FEATURES_append_amdx86 = " cfg/smp.scc"
