DEPENDS_append_amd = " mesa-glut glew"
PACKAGECONFIG_append_amd = " glut glew glu"

bindir_amd = "/opt/${PN}"
EXTRA_OECONF_append_amd = " --bindir=${bindir}"
