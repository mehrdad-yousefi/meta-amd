require linux-yocto-common_5.4.inc

KBRANCH_amdx86 ?= "v5.4/standard/preempt-rt/base"
SRCREV_machine_amdx86 ?= "cae0a59bd5f29cd3e28d47b6bc4b41d385163bad"
SRCREV_meta_amdx86 ?= "94bb91f62b8ea78d1b87eef6fc348035c405726a"
LINUX_VERSION_amdx86 ?= "5.4.15"

include linux-yocto-${MACHINE}_5.4.inc
