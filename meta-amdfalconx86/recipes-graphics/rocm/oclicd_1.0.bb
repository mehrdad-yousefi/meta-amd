DESCRIPTION = "RocM OCL conf file"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://amdocl64.icd;md5=45a4b6c5182d693554c287765b369dfe \
"

SRC_URI = "file://amdocl64.icd \
"

PR = "r0"
PV = "1.0"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/${sysconfdir}/OpenCL/vendors/
    install -m 0644 ${S}/amdocl64.icd ${D}/${sysconfdir}/OpenCL/vendors/
}

