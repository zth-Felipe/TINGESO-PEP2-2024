import httpCommon from "../http-common";

const getAll = () => {
    return httpCommon.get("api/v1/repairs/bonuses/");
}

const get = id => {
    return httpCommon.get(`api/v1/repairs/bonuses/${id}`);
}

const create = data => {
    return httpCommon.post("api/v1/repairs/bonuses/", data);
}

const update = (id,data) => {
    return httpCommon.put(`api/v1/repairs/bonuses/${id}`,data);
}

const remove = id => {
    return httpCommon.delete(`api/v1/repairs/bonuses/${id}`);
}

export default { getAll, create, update, remove, get };