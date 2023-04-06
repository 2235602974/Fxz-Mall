/*
 * COPYRIGHT (C) 2022 Art AUTHORS(fxzcloud@gmail.com). ALL RIGHTS RESERVED.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { DIC_PROPS, DIC_HTTP_PROPS } from '../global/variable'
export default function () {
  return {
    data () {
      return {
        name: '',
        text: undefined,
        propsHttpDefault: DIC_HTTP_PROPS,
        propsDefault: DIC_PROPS
      }
    },
    props: {
      blur: Function,
      focus: Function,
      change: Function,
      click: Function,
      typeformat: Function,
      label: {
        type: String,
        default: ''
      },
      value: {},
      column: {
        type: Object,
        default: () => { }
      },
      disabled: {
        type: Boolean,
        default: false
      },
      placeholder: {
        type: String,
        default: ''
      },
      readonly: {
        type: Boolean,
        default: false
      },
      dataType: {
        type: String
      },
      clearable: {
        type: Boolean,
        default: true
      },
      type: {
        type: String,
        default: ''
      },
      dicUrl: {
        type: String,
        default: ''
      },
      dicMethod: {
        type: String,
        default: ''
      },
      dicQuery: {
        type: Object,
        default: () => {
          return {}
        }
      },
      dic: {
        type: Array,
        default: () => {
          return []
        }
      },
      propsHttp: {
        type: Object,
        default: () => DIC_HTTP_PROPS
      },
      props: {
        type: Object,
        default: () => DIC_PROPS
      }
    },
    watch: {
      value: {
        handler () {
          this.initVal()
        },
        immediate: true
      }
    },
    computed: {
      nameKey: function () {
        return this.propsHttp.name || this.propsHttpDefault.name
      },
      urlKey: function () {
        return this.propsHttp.url || this.propsHttpDefault.url
      },
      resKey: function () {
        return this.propsHttp.res || this.propsHttpDefault.res
      },
      labelKey: function () {
        return this.props.label || this.propsDefault.label
      },
      valueKey: function () {
        return this.props.value || this.propsDefault.value
      },
      childrenKey: function () {
        return this.props.children || this.propsDefault.children
      },
      disabledKey: function () {
        return this.props.disabled || this.propsDefault.disabled
      },
      idKey: function () {
        return this.props.id || this.propsDefault.id
      }
    },
    methods: {
      // 暂时这样初始化
      initVal () {
        this.text = this.value
      }
    }
  }
}
