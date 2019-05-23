require linux-yocto-r1000_4.14.inc

SRC_URI_append_r1000 = " file://r1000-standard-only.cfg \
                         file://r1000-amdgpu-patches.scc \
                         file://r1000-emmc-patches.scc \
                         file://r1000-xgbe-patches.scc \
			 file://r1000-misc-patches.scc"
