require linux-yocto-6.6.inc

PR := "${INC_PR}.0"

SRCREV_machine = "0ccfb8e07e797d57830f3008028de56e22de6e0b"

inherit kernel

do_configure() {
   :; 
}

#S = "${WORKDIR}"

do_install() {
    # Install kernel modules
    install -d ${D}/lib/modules/${KERNEL_VERSION}/
    oe_runmake INSTALL_MOD_PATH=${D}/ modules_install

    # Install kernel image
    install -d ${D}/boot
    if [ -e "${KERNEL_OUTPUT_DIR}/${KERNEL_IMAGETYPE}" ]; then
        install -m 0644 ${KERNEL_OUTPUT_DIR}/${KERNEL_IMAGETYPE} ${D}/boot/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
    fi

    # Install device tree (if applicable)
    if [ -n "${KERNEL_DEVICETREE}" ]; then
        for dtb in ${KERNEL_DEVICETREE}; do
            dtb_base=$(basename ${dtb})
            install -m 0644 ${B}/arch/${ARCH}/boot/dts/${dtb} ${D}/boot/${dtb_base}
        done
    fi

    # Clean up module metadata
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}/Module.symvers
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}/modules.builtin.alias.bin
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}/modules.symbols
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}/modules.softdep
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}/modules.dep.bin
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}/modules.alias
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}/modules.alias.bin
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}/modules.devname
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}/modules.symbols.bin
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}/modules.dep
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}/modules.builtin.bin
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}+/Module.symvers
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}+/modules.builtin.alias.bin
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}+/modules.symbols
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}+/modules.softdep
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}+/modules.dep.bin
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}+/modules.alias
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}+/modules.alias.bin
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}+/modules.devname
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}+/modules.symbols.bin
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}+/modules.dep
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}+/modules.builtin.bin
}

PACKAGES =+ "extra-modules"

EXTRA_OEMAKE += "INSTALL_MOD_STRIP=1"
INSANE_SKIP:${PN} += "already-stripped"

# Conditionally add packages based on machine type
do_siena[prefunc] = "siena_prefunc"
siena_prefunc() {
 # Only add these packages for the 'siena' machine
    if test "${MACHINE}" = "siena"; then
        IMAGE_INSTALL:append = " lscpuinfo git"
    fi
}

DEPENDS += "lib64-setup elfutils elfutils-native"
IMAGE_INSTALL:append = " lib64-setup"
VIRTUAL-RUNTIME_init_manager = "systemd"
