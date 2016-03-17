(require(sp))
(require(cluster))
(require(KernSmooth))
(require(iterators))
#suppressPackageStartupMessages(require(scales))
(require(ggplot2))
(require(flowCore))
(require(lubridate))
(require(GGally))
(require(ellipse))
(require(mvtnorm))
(require(tools))
(require(mixtools))
(require(R.utils))
(require(flowClust))





retrieveCompensationMatrix <- function(file, ...) {
  
  fcs <- suppressWarnings(flowCore::read.FCS(file, ...))
  params <- fcs@parameters
  pd <- pData(params)
  
  get.comp <- function(in_fcs, keyword) {
    compensationMatrix <- description(in_fcs)[[keyword]]
    return(compensationMatrix)
  }
  
  
  if (!is.null(description(fcs)$SPILL)) {
    fcs <- get.comp(fcs, "SPILL")
  } else if (!is.null(description(fcs)$SPILLOVER)) {
    fcs <- get.comp(fcs, "SPILLOVER")
  }
  return(fcs)
}
  
  
  
  