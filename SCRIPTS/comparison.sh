#/bin/bash
compare -verbose -metric mae DATA/GRAPHES/machin.png DATA/GRAPHES/bidule.png DATA/GRAPHES/difference_machin_bidule.png > DATA/TMP/comparison_machin_bidule_log.txt 2>&1
