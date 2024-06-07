import httpCommon from "../http-common";

const getAll = () => {
    return httpCommon.get("api/v1/repairList/");
}

const create = data => {
    return httpCommon.post("api/v1/repairList/", data);
}

const getRepairs =() => {
    return httpCommon.get("api/v1/repairList/types");
}

const update = (data) => {
    return httpCommon.put("api/v1/repairList/", data);
}

const getById = (id) => {
    return httpCommon.get(`api/v1/repairList/${id}`);
}

export default { getAll, create, getRepairs, update, getById};