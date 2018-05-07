TARGETS = mountkernfs.sh alsa-utils ufw x11-common hostname.sh plymouth-log apparmor dns-clean pppd-dns udev keyboard-setup.sh mountdevsubfs.sh resolvconf procps brltty hwclock.sh lvm2 checkroot.sh checkfs.sh networking urandom bootmisc.sh mountall.sh checkroot-bootclean.sh kmod mountnfs.sh mountnfs-bootclean.sh mountall-bootclean.sh
INTERACTIVE = udev keyboard-setup.sh checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup.sh: mountkernfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
resolvconf: dns-clean
procps: mountkernfs.sh udev
brltty: mountkernfs.sh udev
hwclock.sh: mountdevsubfs.sh
lvm2: mountdevsubfs.sh udev
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup.sh
checkfs.sh: lvm2 checkroot.sh
networking: mountkernfs.sh urandom resolvconf procps dns-clean
urandom: hwclock.sh
bootmisc.sh: udev mountnfs-bootclean.sh mountall-bootclean.sh checkroot-bootclean.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
checkroot-bootclean.sh: checkroot.sh
kmod: checkroot.sh
mountnfs.sh: networking
mountnfs-bootclean.sh: mountnfs.sh
mountall-bootclean.sh: mountall.sh
