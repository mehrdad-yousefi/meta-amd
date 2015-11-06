FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_amd = " file://0001-drop-demos-dependant-on-obsolete-MESA_screen_surface.patch"

DEPENDS_append_amd = " mesa-glut glew"
PACKAGECONFIG_append_amd = " glut glew glu"
