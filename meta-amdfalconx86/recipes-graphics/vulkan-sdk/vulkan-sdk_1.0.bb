DESCRIPTION = "Vulkan SDK components"
LICENSE = "BSD"
DEPENDS_append_amd = "libdrm amdvlk"

LIC_FILES_CHKSUM = "file://vulkan-sdk/etc/explicit_layer.d/VkLayer_api_dump.json;md5=62042bf3914a936ee34e4db8f79aeac3 \
"

SRC_URI = "file://vulkan-sdk.tar.bz2 \
"

PR = "r0"
PV = "1.0"

S = "${WORKDIR}"

do_install() {
    install -vd ${D}/${sysconfdir}/explicit_layer.d
    install -m 0644 vulkan-sdk/etc/explicit_layer.d/*.json ${D}${sysconfdir}/explicit_layer.d
    install -vd ${D}/opt
    install -vd ${D}/opt/examples
    install -m 0755 vulkan-sdk/examples/*.spv ${D}/opt/examples
    install -m 0755 vulkan-sdk/examples/lunarg.ppm ${D}/opt/examples
    install -m 0755 vulkan-sdk/examples/cube ${D}/opt/examples
    install -m 0755 vulkan-sdk/examples/tri ${D}/opt/examples
    install -m 0755 vulkan-sdk/examples/vulkaninfo ${D}/opt/examples
    install -vd ${D}/${libdir}
    install -m 0755 vulkan-sdk/lib/*.so ${D}${libdir}
    install -m 0755 vulkan-sdk/lib/*.so.* ${D}${libdir}
}

INSANE_SKIP_${PN} += "ldflags"
INSANE_SKIP_${PN} += "installed-vs-shipped"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_SPLIT = "1"

FILES_${PN} = "${sysconfdir}/explicit_layer.d"
FILES_${PN} += "${libdir}"
FILES_${PN} += "${libdir}/*.so"
FILES_${PN} += "/opt/examples"
FILES_SOLIBSDEV = ""

