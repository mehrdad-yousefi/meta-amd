DESCRIPTION = "These binaries provide kernel support for newer AMD GPUs"
SECTION = "kernel"
LICENSE = "Firmware-amd"

SRC_URI = "file://raven2_asd.bin \
	   file://raven2_ce.bin \
	   file://raven2_gpu_info.bin \
	   file://raven2_me.bin \
	   file://raven2_mec2.bin \
	   file://raven2_mec.bin \
	   file://raven2_pfp.bin \
	   file://raven2_rlc.bin \
	   file://raven2_sdma1.bin \
	   file://raven2_sdma.bin \
	   file://raven2_vcn.bin \
	   file://LICENSE"

LIC_FILES_CHKSUM = "file://LICENSE;md5=07b0c31777bd686d8e1609c6940b5e74"

S = "${WORKDIR}"

# Since, no binaries are generated for a specific target,
# inherit allarch to simply populate prebuilt binaries
inherit allarch

do_compile() {
	:
}

do_install() {
	install -v -m 444 -D ${S}/LICENSE ${D}/lib/firmware/amdgpu/LICENSE
	install -v -m 0644 ${S}/*.bin ${D}/lib/firmware/amdgpu
}

FILES_${PN} = "/lib/firmware/*"
