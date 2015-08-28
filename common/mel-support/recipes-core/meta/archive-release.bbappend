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

#
# The Upstream bb file errantly uses "==" for the conditional check here
# but that won't work on systems where "dash" is the system shell.
#
# Ultimately this should be fixed in meta-mentor but that repo
# is locked down for release at present.
#
git_tar () {
    repo=$1
    shift
    name=`basename $repo`
    if [ -e $repo/.git ]; then
        if [ "${@oe.data.typed_value('RELEASE_USE_TAGS', d)}" = "True" ]; then
            version=$(git --git-dir=$repo/.git describe --tags)
        else
            version=$(git --git-dir=$repo/.git rev-list HEAD | wc -l)
        fi
        git --git-dir=$repo/.git archive --format=tar --prefix="$name/" HEAD | bzip2 >deploy/${name}_${version}.tar.bz2
    else
        release_tar $repo "$@" "--transform=s,^$repo,$name," -cjf deploy/$name.tar.bz2
    fi
}
