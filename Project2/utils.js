function createResult(error,result) {
    var data = {}
    if (error) {
      data["status"] = "error"
      data["error"] = error
    } else {
      data["status"] = "success"
      data["data"] = result
    }
    return data
  }
  
  module.exports = { createResult }