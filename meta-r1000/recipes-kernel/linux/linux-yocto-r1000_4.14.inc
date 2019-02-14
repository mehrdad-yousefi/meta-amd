FILESEXTRAPATHS_prepend := "${THISDIR}/linux-yocto-${LINUX_VERSION}:"

SRC_URI_append_r1000 += "file://r1000-user-features.scc \
    file://r1000-user-patches.scc \
    file://r1000-misc-patches.scc \
    file://r1000.cfg \
    file://r1000-user-config.cfg \
    file://r1000-gpu-config.cfg \
    file://r1000-extra-config.cfg \
"

KERNEL_FEATURES_append_r1000 = " cfg/sound.scc"

COMPATIBLE_MACHINE_r1000 = "r1000"
