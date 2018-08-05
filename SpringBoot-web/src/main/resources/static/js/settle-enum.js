var StepEnum = {
    basic: 1,
    relScenesFailure: 2,
    conditionsFailure: 3,
    segmentFailure: 4,
    down: 5,
    properties: {
        1: {name: "基础信息检查", value: 1, code: "S"},
        2: {name: "关联场景检查", value: 2, code: "M"},
        3: {name: "结酬条件检查", value: 3, code: "L"},
        4: {name: "分段检查", value: 4, code: "K"},
        5: {name: "检查完毕", value: 5, code: "D"}
    }
};