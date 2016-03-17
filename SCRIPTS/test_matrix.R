require(flowCore)
require(flowClust)
library(flowViz)
source("SCRIPTS/fcs.R")
source("SCRIPTS/library.R")
#x <- read.FCS(file.path("/home/foulquier/Bureau/SpellCraft/flowBeads-master/nathan/QC8PeaksBeads_32140203_SAS_ARIA_16MAR2015_16MAR2015.fcs"))
#y <- retrieveCompensationMatrix(file.path("/home/foulquier/Bureau/SpellCraft/flowBeads-master/nathan/QC8PeaksBeads_32140203_SAS_ARIA_16MAR2015_16MAR2015.fcs"))
x <- flowCore::read.FCS(file.path("/home/foulquier/Bureau/SpellCraft/flowBeads-master/nathan/QC8PeaksBeads_32140203_SAS_ARIA_16MAR2015_16MAR2015.fcs"))
png(filename="DATA/FrameWM.png")
plot(x)
dev.off()

y <- retrieveCompensationMatrix(file.path("/home/foulquier/Bureau/SpellCraft/flowBeads-master/nathan/QC8PeaksBeads_32140203_SAS_ARIA_16MAR2015_16MAR2015.fcs"))
y[1,5] <- 0.4
y[1,4] <- 0.9
y[5,6] <- 1

z <- compensate(x, y)
png(filename="DATA/FrameM.png")
plot(z)
dev.off()

#z <- read.FCS(file.path("/home/foulquier/Bureau/SpellCraft/flowBeads-master/nathan/QC8PeaksBeads_32140203_SAS_ARIA_16MAR2015_16MAR2015.fcs"), spillover=y)
#v <- retrieveCompensationMatrix(file.path("/home/foulquier/Bureau/SpellCraft/flowBeads-master/nathan/QC8PeaksBeads_32140203_SAS_ARIA_16MAR2015_16MAR2015.fcs"))
