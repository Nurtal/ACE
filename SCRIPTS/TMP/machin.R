require(flowCore)
require(flowClust)
library(flowViz)
source("SCRIPTS/fcs.R")
x <- flowCore::read.FCS(file.path("/home/foulquier/Bureau/SpellCraft/flowBeads-master/nathan/QC8PeaksBeads_32140203_SAS_ARIA_16MAR2015_16MAR2015.fcs"))
summary(x)
png(filename="DATA/GRAPHES/machin.png")
plot(x)
dev.off()
