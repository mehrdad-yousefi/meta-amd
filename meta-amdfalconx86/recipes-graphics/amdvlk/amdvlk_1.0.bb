DESCRIPTION = "AMD Vulkan Library"
LICENSE = "BSD"
DEPENDS_append_amd = "libdrm"

LIC_FILES_CHKSUM = "file://amdvulkan-lib/etc/vulkan/icd.d/amd_icd64.json;md5=d50b88cc7729eebbedb4ec4f601097d5 \
"

SRC_URI = "file://amdvulkan-lib.tar.bz2 \
"

PR = "r0"
PV = "1.0"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/${sysconfdir}/vulkan/
    install -d ${D}/${sysconfdir}/vulkan/icd.d
    install -m 0644 amdvulkan-lib/etc/vulkan/icd.d/amd_icd64.json ${D}/${sysconfdir}/vulkan/icd.d/
    install -vd ${D}/opt
    install -vd ${D}/opt/amdgpu-pro
    install -vd ${D}/opt/amdgpu-pro/lib
    install -vd ${D}/opt/amdgpu-pro/lib/x86_64-linux-gnu
    install -m 0755 amdvulkan-lib/opt/amdgpu-pro/lib/x86_64-linux-gnu/*.so ${D}/opt/amdgpu-pro/lib/x86_64-linux-gnu
}

INSANE_SKIP_${PN} += "already-stripped"
INSANE_SKIP_${PN} += "ldflags"
INSANE_SKIP_${PN} += "installed-vs-shipped"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_SPLIT = "1"

FILES_${PN} = "${sysconfdir}/vulkan"
FILES_${PN} = "${sysconfdir}/vulkan/icd.d"
FILES_${PN} += "/opt/amdgpu-pro/lib/x86_64-linux-gnu/amdvlk64.so"
FILES_SOLIBSDEV = ""


