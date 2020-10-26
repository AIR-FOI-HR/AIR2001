using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class PromocijaNaLokaciji
    {
        public int LokacijaIdLokacija { get; set; }
        public int PromocijaIdPromocija { get; set; }

        public virtual Lokacija LokacijaIdLokacijaNavigation { get; set; }
        public virtual Promocija PromocijaIdPromocijaNavigation { get; set; }
    }
}
