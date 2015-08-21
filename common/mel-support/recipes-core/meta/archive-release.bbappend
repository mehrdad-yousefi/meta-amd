IMAGE_EXTENSION_live_amd := "${@oe_filter_out('iso', '${IMAGE_EXTENSION_live}', d)}"

#
# The Upstream bb file errantly uses "==" for this conditional
# but that won't work on systems where "dash" is the system shell.
#
# Ultimately this should be fixed in meta-mentor but that repo
# is locked down for release at present.
#
prepare_templates_append() {
    if [ "${DISTRO}" = "mel-lite" ]; then
        sed -i 's,^\(EXTERNAL_TOOLCHAIN.*/codebench\)"$,\1-lite",' local.conf.sample
    fi
}
