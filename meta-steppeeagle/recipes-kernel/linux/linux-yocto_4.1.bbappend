FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR := "${INC_PR}.1"

KBRANCH_steppeeagle ?= "standard/base"
KMACHINE_steppeeagle ?= "common-pc-64"
COMPATIBLE_MACHINE_steppeeagle = "steppeeagle"
KERNEL_FEATURES_append_steppeeagle += " cfg/smp.scc cfg/sound.scc"

SRC_URI_append_steppeeagle += " file://steppeeagle-user-features.scc \
 				file://steppeeagle-user-patches.scc \
				file://steppeeagle-standard.scc \
				file://steppeeagle_savedefconfig.cfg \
				file://logo.cfg \
				file://usb-serial.cfg \
				file://wifi-drivers.cfg \
				file://console.cfg \
				file://hid.cfg \
				file://efi-partition.cfg \
				file://sound.cfg \
				file://steppeeagle-gpu-config.cfg \
				file://cpu_family.cfg \
				file://microcode.cfg \
				${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'file://enable-bluetooth.cfg', 'file://disable-bluetooth.cfg', d)} \
				${@bb.utils.contains('DISTRO', 'mel', 'file://enable-kgdb.cfg', 'file://disable-kgdb.cfg', d)} \
"

# Not available in amdfalconx86.  Do we really need these?
SRC_URI_append_steppeeagle += " file://disable-debug-preempt.cfg \
				file://disable-sata-pmp.cfg \
"

# strip trailing ';' to workaround bb.fetch.URI bug
python () {
	src_uri = d.getVar('SRC_URI', True).split()
	d.setVar('SRC_URI', ' '.join(s.rstrip(";") for s in src_uri))
}
