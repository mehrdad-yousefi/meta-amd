require ffmpeg.inc

LICENSE = "LGPLv2.1+ & GPLv2+"
PR = "${INC_PR}.2"

SRC_URI = "http://ffmpeg.org/releases/ffmpeg-${PV}.tar.bz2"
SRC_URI[md5sum] = "989d9024313c2b7e2eeaed58b751c0ee"
SRC_URI[sha256sum] = "3b6d9951533323ee64a21d0aa7667a780b3470bfe4e0fb7c1b33307ce290615a"

S = "${WORKDIR}/${BP}"

LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://libpostproc/postprocess.c;beginline=8;endline=21;md5=3163771dd725805aeda961a4e05173b5 \
                    file://COPYING.LGPLv2.1;md5=bd7a443320af8c812e4c18d1b79df004 \
                    file://ffmpeg.c;beginline=7;endline=20;md5=0385751f4c95cb9267cc125532118221"

EXTRA_OECONF = " \
	--arch=x86_64 \
        --cross-prefix=${TARGET_PREFIX} \
        --disable-stripping \
        --enable-cross-compile \
        --enable-libtheora  \
        --enable-libvorbis \
        --enable-pthreads \
        --enable-shared \
        --enable-swscale \
        --enable-vaapi \
	--enable-vdpau \
        --enable-gpl \
        --enable-yasm \
        --extra-cflags="${TARGET_CFLAGS}" \
        --extra-ldflags="${TARGET_LDFLAGS}" \
        --sysroot="${STAGING_DIR_TARGET}" \
        --prefix=${prefix} \
	--libdir=${libdir} \
	--pkgconfigdir=${libdir}/pkgconfig \
	--target-os=linux \
"

do_configure() {
	cd ${S}
	./configure ${EXTRA_OECONF}
}

do_install() {
	cd ${S}
	oe_runmake DESTDIR=${D} install
}

FILES_${PN} += "/usr/lib64/libswresample.so.* ${datadir}/examples ${datadir}/ffprobe.xsd"
FILES_${PN}-dev += "/usr/lib64/libswresample.so /usr/lib64/pkgconfig/libswresample.pc"
