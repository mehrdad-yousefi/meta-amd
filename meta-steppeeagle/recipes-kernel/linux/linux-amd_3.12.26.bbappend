FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += " \
	   file://0045-yocto-amd-drm-radeon-add-Mullins-chip-family.patch \
	   file://0046-yocto-amd-drm-radeon-update-cik-init-for-Mullins.patch \
	   file://0047-yocto-amd-drm-radeon-add-Mullins-UVD-support.patch \
	   file://0048-yocto-amd-drm-radeon-add-Mullins-dpm-support.patch \
	   file://0049-yocto-amd-drm-radeon-modesetting-updates-for-Mullins.patch \
	   file://0050-yocto-amd-drm-radeon-add-pci-ids-for-Mullins.patch \
	   file://0051-yocto-amd-drm-radeon-add-Mulins-VCE-support.patch \
"
