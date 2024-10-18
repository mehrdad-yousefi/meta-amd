
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-amd:"


SRC_URI:append:siena := " \
           file://ntb.scc \
           file://ntb.cfg \
           file://amd_io.cfg \
	   file://prof.scc \
	   file://prof.cfg \
	  "

SRC_URI:append:turin := " \
           file://ntb.scc \
           file://ntb.cfg \
           file://amd_io.cfg \
	   file://prof.scc \
	   file://prof.cfg \
	  "

