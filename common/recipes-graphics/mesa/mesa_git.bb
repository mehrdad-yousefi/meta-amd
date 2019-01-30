require recipes-graphics/mesa/${BPN}.inc

S = "${WORKDIR}/git"

DEPENDS_append = " python-mako-native"
inherit pythonnative

SRCREV_amd = "0144bbdb98f515cce31688fce57d769e7fd82d52"
LIC_FILES_CHKSUM_amd = "file://docs/license.html;md5=725f991a1cc322aa7a0cd3a2016621c4"
PV_amd = "19.0.0+git${SRCPV}"

DEPENDS_append_amd = " libvdpau libomxil"

PACKAGECONFIG[va] = "--enable-va,--disable-va,libva"
PACKAGECONFIG_append_amd = " xvmc gallium r600 gallium-llvm xa"
PACKAGECONFIG_append_radeon = " va"
PACKAGECONFIG_append_amdgpu = " va"

PACKAGECONFIG_remove_amd = "vulkan"
PACKAGECONFIG_remove_amdfalconx86 = "xvmc"

LIBVA_PLATFORMS  = "libva"
LIBVA_PLATFORMS .= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', ' libva-x11', '', d)}"
LIBVA_PLATFORMS .= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', ' libva-wayland', '', d)}"
RDEPENDS_mesa-megadriver += "${@bb.utils.contains('PACKAGECONFIG', 'va', '${LIBVA_PLATFORMS}', '', d)}"

MESA_LLVM_RELEASE_amd = "7"

SRC_URI_amd = "git://anongit.freedesktop.org/mesa/mesa;branch=master \
               file://0001-configure.ac-obey-llvm_prefix-if-available.patch \
               file://0001-configure.ac-adjust-usage-of-LLVM-flags.patch"

EXTRA_OECONF_append_amd = " \
		 --enable-vdpau \
		 --enable-osmesa \
		 --enable-glx \
		 --enable-omx-bellagio \
		 --with-omx-bellagio-libdir=${libdir}/bellagio \
		 --enable-texture-float"

# Package all the libXvMC gallium extensions together
# they provide the shared lib libXvMCGallium and splitting
# them up creates trouble in rpm packaging
PACKAGES =+ "libxvmcgallium-${PN} libxvmcgallium-${PN}-dev"
FILES_libxvmcgallium-${PN} = "${libdir}/libXvMC*${SOLIBS}"
FILES_libxvmcgallium-${PN}-dev = "${libdir}/libXvMC*${SOLIBSDEV} \
                               ${libdir}/libXvMC*.la"

PACKAGES =+ "libvdpau-${PN} libvdpau-${PN}-dev"
FILES_libvdpau-${PN} = "${libdir}/vdpau/libvdpau*${SOLIBS}"
FILES_libvdpau-${PN}-dev = "${libdir}/vdpau/libvdpau*${SOLIBSDEV} \
                            ${libdir}/vdpau/libvdpau*.la"
FILES_${PN}-dbg += "${libdir}/vdpau/.debug"

#
# libomx files are non-versioned so we put *.so directly in the
# main package as opposed to the -dev package.
#
PACKAGES =+ "libomx-${PN} libomx-${PN}-dev"
FILES_libomx-${PN} = "${libdir}/bellagio/libomx_*.so"
FILES_libomx-${PN}-dev = "${libdir}/bellagio/libomx_*.la"
FILES_${PN}-dbg += "${libdir}/bellagio/.debug"
INSANE_SKIP_${PN} += "installed-vs-shipped"

# Set DRIDRIVERS with anonymous python so we can effectively
# override the _append_x86-64 assignement from mesa.inc.
python () {
    d.setVar("DRIDRIVERS", "swrast,radeon")
    d.setVar("GALLIUMDRIVERS", "swrast,r300,r600,radeonsi")
}

#because we cannot rely on the fact that all apps will use pkgconfig,
#make eglplatform.h independent of MESA_EGL_NO_X11_HEADER
do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'egl', 'true', 'false', d)}; then
        sed -i -e 's/^#if defined(MESA_EGL_NO_X11_HEADERS)$/#if defined(MESA_EGL_NO_X11_HEADERS) || ${@bb.utils.contains('PACKAGECONFIG', 'x11', '0', '1', d)}/' ${D}${includedir}/EGL/eglplatform.h
    fi
}

