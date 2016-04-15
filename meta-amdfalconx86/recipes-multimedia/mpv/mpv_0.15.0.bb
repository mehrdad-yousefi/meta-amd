DESCRIPTION = "mpv is a media player based on Mplayer and mplayer2. \
	       It supports a wide variety of video file formats, \
	       audio and video codecs, and subtitle types. Special \
	       input URL types are available to read input from a \
	       variety of sources other than disk files. Depending \
	       on platform, a variety of different video and audio\
	       output methods are supported."
HOMEPAGE = "https://mpv.io"
LICENSE = "GPLv2"
DEPENDS = "ffmpeg bzip2 jpeg pulseaudio alsa-lib"
RDEPENDS_${PN} = "ffmpeg libxrandr libxv libxscrnsaver libxinerama libvdpau mesa"
LIC_FILES_CHKSUM = "file://LICENSE;md5=91f1cb870c1cc2d31351a4d2595441cb"

SRC_URI = "https://github.com/mpv-player/mpv/archive/v0.15.0.tar.gz"
SRC_URI[md5sum] = "9042bd3fbff2bc8ba0b7fadaa4a22101"
SRC_URI[sha256sum] = "7d31217ba8572f364fcea2955733f821374ae6d8c6d8f22f8bc63c44c0400bdc"

S = "${WORKDIR}/mpv-${PV}"

do_configure() {
	cd ${S}
	./bootstrap.py
	./waf configure --prefix=${D}/usr --disable-libass
}

do_compile() {
	cd ${S}
	./waf build
}

do_install() {
	cd ${S}
	./waf install 
}

FILES_${PN} += "/usr/share/icons /usr/etc"
