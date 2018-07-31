DESCRIPTION = "Radeon GPU Profiler"
LICENSE = "LGPL3"

LIC_FILES_CHKSUM = "file://RadeonGPUProfiler_1.2.0.33/License.rtf;md5=5bd0909528eb8887c94f56227330fc8b \
"

SRC_URI = "file://RadeonGPUProfiler_1.2.0.33/ \
"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/opt/rgp
    cp -rf  ${S}/RadeonGPUProfiler_1.2.0.33/* ${D}/opt/rgp
    chmod -R 550 ${D}/opt/rgp
}

INSANE_SKIP_${PN} += "already-stripped ldflags file-rdeps"

FILES_${PN} += "opt/*"
FILES_SOLIBSDEV = ""
